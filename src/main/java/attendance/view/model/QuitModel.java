package attendance.view.model;

public record QuitModel() implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
