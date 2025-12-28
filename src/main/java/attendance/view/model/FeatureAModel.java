package attendance.view.model;

import java.time.LocalDateTime;

public record FeatureAModel(LocalDateTime dateTime, attendance.constant.AttendanceState attendanceState) implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
