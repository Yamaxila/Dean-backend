package by.vstu.dean.manager;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagerRequestModel {

    private String header;
    private String data;

    private ManagerProcessConfiguration configuration;

}