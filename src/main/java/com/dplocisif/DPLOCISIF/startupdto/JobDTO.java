package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "GetJobListPr",
        classes = @ConstructorResult(
                targetClass = JobDTO.class,
                columns = {
                        @ColumnResult(name = "depo_code", type = String.class), // adjust type as needed
                        @ColumnResult(name = "job_code", type = String.class),
                        @ColumnResult(name = "job_description", type = String.class)
                }
        )
)
public class JobDTO {
    @Id
    private String depoCode;
    private String jobCode;
    private String jobDescription;
}
