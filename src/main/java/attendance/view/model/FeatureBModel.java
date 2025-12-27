package attendance.view.model;

public record FeatureBModel(int a, int b, int sum) implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
