package attendance.view.model;

public record FeatureAModel(String name) implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
