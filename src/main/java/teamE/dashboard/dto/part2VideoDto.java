package teamE.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import teamE.dashboard.entity.Department;
import teamE.dashboard.entity.Disease;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter@Setter@AllArgsConstructor
public class part2VideoDto {

    private Long id;
    private int hit;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Disease disease;

    public part2VideoDto() {
    }
}
