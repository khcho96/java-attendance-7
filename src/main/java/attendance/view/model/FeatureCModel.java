package attendance.view.model;

import java.time.LocalDate;

public record FeatureCModel(attendance.domain.Crew crew, LocalDate now) implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
