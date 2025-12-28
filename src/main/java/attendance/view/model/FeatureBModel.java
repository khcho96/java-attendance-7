package attendance.view.model;

public record FeatureBModel(java.time.LocalDateTime oldDateTime, java.time.LocalDateTime newDateTime, attendance.constant.AttendanceState newAttendanceState) implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
