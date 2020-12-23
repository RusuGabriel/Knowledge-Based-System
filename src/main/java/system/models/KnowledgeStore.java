package system.models;

public class KnowledgeStore {
    private static KnowledgeStore instance = null;

    private KnowledgeStore() {
    }

    public static KnowledgeStore getInstance() {
        if (instance == null) {
            synchronized (KnowledgeStore.class) {
                if (instance == null)
                    instance = new KnowledgeStore();
            }
        }
        return instance;
    }

}
