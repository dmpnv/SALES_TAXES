package taxcalculator;

import java.util.List;
import static taxcalculator.GoodsItem.Type.BOOK;
import static taxcalculator.GoodsItem.Type.FOOD;
import static taxcalculator.GoodsItem.Type.MEDICAL_PRODUCT;

/**
 *
 * @author dpanov
 */
public class TaxCalculator
{
    public static final double IMPORT_TAX_RATE = 0.05;
    public static final double TAX_RATE = 0.1;

    public ItemTaxes applyTaxes(GoodsItem goodsItem)
    {
        GoodsItem.Type type = goodsItem.getType();
        double taxValue = 0;
        
        if ( type != BOOK && type != FOOD && type != MEDICAL_PRODUCT )
        {
            taxValue += goodsItem.getAmount() * goodsItem.getPrice() * TAX_RATE;
            taxValue = Math.ceil( taxValue * 20.0 ) / 20.0;
        }
        
        if ( goodsItem.isImported() )
        {
            taxValue += goodsItem.getAmount() * goodsItem.getPrice() * IMPORT_TAX_RATE;
            taxValue = Math.ceil( taxValue * 20.0 ) / 20.0;
        }
        
        return new ItemTaxes( goodsItem, taxValue );
    }
    
    public Totals calcTotals( List<ItemTaxes> printList )
    {
        Totals totals = new Totals();
        for( ItemTaxes itemTaxes : printList )
            totals.add( itemTaxes );
        
        return totals;
    }
    
    public void printResult( List<ItemTaxes> printList, Totals totals )
    {
        for( ItemTaxes itemTaxes : printList )
        {
            GoodsItem item = itemTaxes.getItem();
            System.out.printf( "%.0f %s: %.2f\n", item.getAmount(), item.getName(), itemTaxes.getNetPrice());
        }
        System.out.printf( "Sales Taxes: %.2f\n", totals.getTotalTaxes() );
        System.out.printf( "Total: %.2f\n", totals.getTotalSum() );
    }

}
