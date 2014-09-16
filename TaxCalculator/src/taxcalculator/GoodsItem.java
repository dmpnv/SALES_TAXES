package taxcalculator;

/**
 *
 * @author dpanov
 */
public class GoodsItem
{
    public enum Type {
        GOOD, BOOK, FOOD, MEDICAL_PRODUCT
    };
    
    private final Type type;
    private final String name;
    private final double price;
    private final double amount;
    private final boolean imported;

    public GoodsItem( String name, double price, double amount, Type type, boolean imported )
    {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.type = type;
        this.imported = imported;
    }
    
    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public double getAmount()
    {
        return amount;
    }

    public Type getType()
    {
        return type;
    }
    
    public boolean isImported()
    {
        return imported;
    }

    @Override
    public String toString()
    {
        return "GoodsItem{" + "type=" + type + ", name=" + name +
                ", price=" + price + ", amount=" + amount + ", isImported=" + imported + '}';
    }
        
    
}
