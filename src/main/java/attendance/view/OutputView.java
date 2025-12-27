package attendance.view;

import attendance.view.model.FeatureAModel;
import attendance.view.model.FeatureBModel;
import attendance.view.model.FeatureCModel;
import attendance.view.model.FeatureDModel;
import attendance.view.model.QuitModel;
import attendance.view.model.ViewModel;
import attendance.view.model.ViewModelVisitor;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String _MESSAGE = "";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    private static final OutputViewVisitor VISITOR = new OutputViewVisitor();

    private OutputView() {}

    public static void render(ViewModel model) {
        model.accept(VISITOR);
    }

    private static final class OutputViewVisitor implements ViewModelVisitor {

        @Override
        public void visit(FeatureAModel model) {
            System.out.println("인사 결과");
            System.out.println("안녕하세요, " + model.name() + "!");
        }

        @Override
        public void visit(FeatureBModel model) {
            System.out.println("합계 결과");
            System.out.println(model.a() + " + " + model.b() + " = " + model.sum());
        }

        @Override
        public void visit(FeatureCModel model) {
            System.out.println("단어 목록 (" + model.words().size() + "개)");
            for (int i = 0; i < model.words().size(); i++) {
                System.out.println((i + 1) + ". " + model.words().get(i));
            }
        }

        @Override
        public void visit(FeatureDModel model) {
            System.out.println(model.title());
            System.out.println(model.message());
        }

        @Override
        public void visit(QuitModel model) {
            // 출력 없음
        }
    }
}
