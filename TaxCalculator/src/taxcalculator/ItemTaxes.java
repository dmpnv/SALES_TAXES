package taxcalculator;

/**
 *
 * @author dpanov
 */
public class ItemTaxes
{
    private final GoodsItem item;
    private final double taxValue;

    public ItemTaxes( GoodsItem item, double taxValue )
    {
        this.item = item;
        this.taxValue = taxValue;
    }

    public GoodsItem getItem()
    {
        return item;
    }

    public double getTaxValue()
    {
        return taxValue;
    }
    
    public double getItemCost()
    {
        return Math.round( 100.0 * item.getPrice()*item.getAmount() ) / 100.0;
    }
        
    public double getNetPrice()
    {
        return Math.round( 100.0 * (getItemCost() + taxValue) ) / 100.0;
    }
}
