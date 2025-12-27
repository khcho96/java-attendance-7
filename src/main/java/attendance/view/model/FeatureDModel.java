package attendance.view.model;

public record FeatureDModel(String title, String message) implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
