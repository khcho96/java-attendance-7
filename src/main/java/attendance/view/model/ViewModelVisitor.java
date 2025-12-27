package attendance.view.model;

public interface ViewModelVisitor {
    void visit(FeatureAModel model);
    void visit(FeatureBModel model);
    void visit(FeatureCModel model);
    void visit(FeatureDModel model);
    void visit(QuitModel model);
}
