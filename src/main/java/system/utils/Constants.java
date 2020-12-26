package system.utils;

/**
 * Constants
 */
public final class Constants {
    public static final class Screens {
        public static final String HOME = "/templates/HomeScreen.fxml";
        public static final String KNOWLEDGE_BASE = "/templates/KnowledgeBaseScreen.fxml";
		public static final String RULES = "/templates/RulesScreen.fxml";

        private Screens() {
        }
    }

    public static final class Type {
        public static final String PHONE = "phone";
        public static final String TABLET = "tablet";
        public static final String WATCH = "watch";

        private Type() {
        }
    }

    private Constants(){}
}