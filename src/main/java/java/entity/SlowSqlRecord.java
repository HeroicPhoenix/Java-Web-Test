package java.entity;

public class SlowSqlRecord {

    private Long id;
    private String category;
    private String statement;
    private String optimizationStrategy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getOptimizationStrategy() {
        return optimizationStrategy;
    }

    public void setOptimizationStrategy(String optimizationStrategy) {
        this.optimizationStrategy = optimizationStrategy;
    }
}
