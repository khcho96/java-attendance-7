package attendance.dto;

import attendance.domain.Crew;

public record RecordQueryResult(Crew crew) {
    public static RecordQueryResult from(Crew crew) {
        return new RecordQueryResult(crew);
    }
}
