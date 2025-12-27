package attendance.view.model;

import java.util.List;

public record FeatureCModel(List<String> words) implements ViewModel {
    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
