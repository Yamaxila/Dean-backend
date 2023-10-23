package by.vstu.dean.services;

import by.vstu.dean.dto.future.rooms.ClassroomDTO;
import by.vstu.dean.dto.mapper.ClassroomMapper;
import by.vstu.dean.enums.EClassroomType;
import by.vstu.dean.enums.EFrame;
import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.rooms.ClassroomModel;
import by.vstu.dean.future.repo.ClassroomModelRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Cacheable("classroom")
public class ClassroomService extends BaseService<ClassroomDTO, ClassroomModel, ClassroomMapper, ClassroomModelRepository> {

    private final DepartmentService departmentService;

    public ClassroomService(ClassroomModelRepository repo, ClassroomMapper mapper, DepartmentService departmentService) {
        super(repo, mapper);
        this.departmentService = departmentService;
    }


    public List<ClassroomModel> updateFromExcel(String filePath) {

        try(FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<ClassroomModel> nClassRooms = new ArrayList<>();
            List<ClassroomModel> classroomModelList = this.getAll();

            for(int i = 1; i < sheet.getLastRowNum(); i++) {

                ClassroomModel classroomModel = new ClassroomModel();
                classroomModel.setStatus(EStatus.ACTIVE);
                classroomModel.setSourceId(0L);
                classroomModel.setCreated(LocalDateTime.now());
                classroomModel.setUpdated(LocalDateTime.now());

                Row row = sheet.getRow(i);
                classroomModel.setFrame(EFrame.valueOf((int)row.getCell(0).getNumericCellValue()));
                Cell au = row.getCell(1);
                au.setCellType(CellType.STRING);
                classroomModel.setRoomNumber(au.getStringCellValue());
                classroomModel.setRoomType(EClassroomType.byName(row.getCell(2).getStringCellValue()));
                classroomModel.setDepartment(null);
                classroomModel.setSeatsNumber((int)row.getCell(3).getNumericCellValue());
                classroomModel.setSquare(row.getCell(4).getNumericCellValue());
                if(classroomModelList.stream().noneMatch(p -> p.getFrame().equals(classroomModel.getFrame()) && p.getRoomNumber().equalsIgnoreCase(classroomModel.getRoomNumber())))
                    nClassRooms.add(classroomModel);
            }


            return this.saveAll(nClassRooms);


        } catch (Exception ignored) {
            ignored.printStackTrace();
            return new ArrayList<>();
        }
    }
}
