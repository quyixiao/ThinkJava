package wjs.entity;

public class Model  {
    public Long id;

    public Model() {
    }

    public Long getId() {
        return this.id;
    }

    public Object _key() {
        return this.getId();
    }
}
