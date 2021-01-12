package system.utils;

/**
 * Constants
 */
public final class Constants {
    public static final class Screens {
        public static final String HOME = "/templates/HomeScreen.fxml";
        public static final String KNOWLEDGE_BASE = "/templates/KnowledgeBaseScreen.fxml";
		public static final String INFO = "/templates/InfoAndSettingsScreen.fxml";

        private Screens() {
        }
    }

    public static final class Type {
        public static final String PHONE = "phone";
        public static final String TABLET = "tablet";
        public static final String WATCH = "watch";

        public static final String PHONES = "Phones";
        public static final String TABLETS = "Tablets";
        public static final String WATCHES = "Watches";

        private Type() {
        }
    }

    private Constants(){}
}