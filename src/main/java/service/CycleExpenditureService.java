package service;

import dto.ExpenditureDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;




@Service
public class CycleExpenditureService {
    @Data
    @Accessors(chain = true)
    private class ExpenditureCyclicDto {
        ExpenditureDto expenditureDto;
        int howOftenInDays;
        int daysMissed;
    }

    public void addExpenditure(ExpenditureDto dto, int howOftenInDays) {
        ExpenditureCyclicDto cyclicDto = new ExpenditureCyclicDto()
                .setExpenditureDto(dto)
                .setHowOftenInDays(howOftenInDays)
                .setDaysMissed(0);
        expenditures.add(cyclicDto);
    }

    public void runAllTasks() {
        while (true) {
            try {
                Thread.sleep(24 * 60 * 60);
                expenditures.stream().forEach(cyclic -> {
                    if (cyclic.getDaysMissed() == cyclic.getHowOftenInDays()) {
                        ExpenditureDto expenditureDto = cyclic.getExpenditureDto();
                        expenditureDto.setTime(LocalDateTime.now());
                        this.expenditureService.create(expenditureDto);
                    } else {
                        cyclic.setDaysMissed(cyclic.getDaysMissed() + 1);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<ExpenditureCyclicDto> expenditures = new ArrayList<>();

    @Inject
    protected ExpenditureService expenditureService;
}
