package com.ess.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetPunchInAndOutRequest {
    private String day;
    private String month;
    private String year;

    @Override
    public String toString() {
        return "GetPunchInAndOutRequest{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
