package Products.model;

/**
 * Created by M4teo on 20.03.2017.
 */

import javafx.beans.property.*;

public class Product
{
    private final StringProperty name;
    private final IntegerProperty amount;
    private final ObjectProperty<ProductType> type;
    private final BooleanProperty refundability;

    public Product(String name, int amount, ProductType type, boolean refundability)
    {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.type = new SimpleObjectProperty<>(type);
        this.refundability = new SimpleBooleanProperty(refundability);
    }

    public Product()
    {
        this(null, 0, null, false);
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public int getAmount()
    {
        return amount.get();
    }

    public void setAmount(int amount)
    {
        this.amount.set(amount);
    }

    public ProductType getType()
    {
        return type.get();
    }

    public void setType(ProductType type)
    {
        this.type.set(type);
    }

    public boolean isRefundability()
    {
        return this.refundability.get();
    }
    public void setRefundability(boolean refundability)
    {
        this.refundability.set(refundability);
    }

    public StringProperty productNameProperty(){
        return name;
    }

    public ObjectProperty<ProductType> productTypeObjectProperty(){
        return type;
    }

    public IntegerProperty amountOfProductProperty(){
        return amount;
    }

    public BooleanProperty isRefundabilityProperty(){
        return refundability;
    }
}

