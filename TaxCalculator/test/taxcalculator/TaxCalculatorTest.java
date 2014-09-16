package taxcalculator;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static taxcalculator.GoodsItem.Type.BOOK;
import static taxcalculator.GoodsItem.Type.FOOD;
import static taxcalculator.GoodsItem.Type.MEDICAL_PRODUCT;
import static taxcalculator.GoodsItem.Type.GOOD;

/**
 *
 * @author dpanov
 */
public class TaxCalculatorTest
{
    
    @Test
    public void test1()
    {
        System.out.println( "test1 ..." );
        TaxCalculator taxCalculator = new TaxCalculator();
        GoodsItem item1 = new GoodsItem( "book", 12.49, 1, BOOK, false );
        GoodsItem item2 = new GoodsItem( "music CD", 14.99, 1, GOOD, false );
        GoodsItem item3 = new GoodsItem( "chocolate bar", 0.85, 1, FOOD, false );
        
        ItemTaxes itemTaxes1 = taxCalculator.applyTaxes( item1 );
        assertEquals( 12.49, itemTaxes1.getNetPrice(), Double.MIN_NORMAL );
        
        ItemTaxes itemTaxes2 = taxCalculator.applyTaxes( item2 );
        assertEquals( 16.49, itemTaxes2.getNetPrice(), Double.MIN_NORMAL );
        
        ItemTaxes itemTaxes3 = taxCalculator.applyTaxes( item3 );
        assertEquals( 0.85, itemTaxes3.getNetPrice(), Double.MIN_NORMAL );
        
        List<ItemTaxes> list = Arrays.asList( itemTaxes1, itemTaxes2, itemTaxes3 );
        
        Totals totals = taxCalculator.calcTotals( list );
        assertEquals( 1.5, totals.getTotalTaxes(), Double.MIN_NORMAL );
        assertEquals( 29.83, totals.getTotalSum(), Double.MIN_NORMAL );
        
        taxCalculator.printResult( list, totals );
        
        System.out.println( "\n" );
    }
    
    @Test
    public void test2()
    {
        System.out.println( "test2 ..." );
        TaxCalculator taxCalculator = new TaxCalculator();
        GoodsItem item1 = new GoodsItem( "imported box of chocolates", 10.0, 1, FOOD, true );
        GoodsItem item2 = new GoodsItem( "imported bottle of perfume", 47.5, 1, GOOD, true );
        
        ItemTaxes itemTaxes1 = taxCalculator.applyTaxes( item1 );
        assertEquals( 10.5, itemTaxes1.getNetPrice(), Double.MIN_NORMAL );
        
        ItemTaxes itemTaxes2 = taxCalculator.applyTaxes( item2 );
        assertEquals( 54.65, itemTaxes2.getNetPrice(), Double.MIN_NORMAL );
        
        List<ItemTaxes> list = Arrays.asList( itemTaxes1, itemTaxes2 );
        Totals totals = taxCalculator.calcTotals( list );
        assertEquals( 7.65, totals.getTotalTaxes(), Double.MIN_NORMAL );
        assertEquals( 65.15, totals.getTotalSum(), Double.MIN_NORMAL );
        taxCalculator.printResult( list, totals );
        System.out.println( "\n" );
    }

    @Test
    public void test3()
    {
        System.out.println( "test3 ..." );
        TaxCalculator taxCalculator = new TaxCalculator();
        GoodsItem item1 = new GoodsItem( "imported bottle of perfume", 27.99, 1, GOOD, true );
        GoodsItem item2 = new GoodsItem( "bottle of perfume", 18.99, 1, GOOD, false );
        GoodsItem item3 = new GoodsItem( "packet of headache pills", 9.75, 1, MEDICAL_PRODUCT, false );
        GoodsItem item4 = new GoodsItem( "imported box of chocolates", 11.25, 1, FOOD, true );
        
        ItemTaxes itemTaxes1 = taxCalculator.applyTaxes( item1 );
        assertEquals( 32.19, itemTaxes1.getNetPrice(), Double.MIN_NORMAL );
        
        ItemTaxes itemTaxes2 = taxCalculator.applyTaxes( item2 );
        assertEquals( 20.89, itemTaxes2.getNetPrice(), Double.MIN_NORMAL );
        
        ItemTaxes itemTaxes3 = taxCalculator.applyTaxes( item3 );
        assertEquals( 9.75, itemTaxes3.getNetPrice(), Double.MIN_NORMAL );
        
        ItemTaxes itemTaxes4 = taxCalculator.applyTaxes( item4 );
        assertEquals( 11.85, itemTaxes4.getNetPrice(), Double.MIN_NORMAL );
        
        List<ItemTaxes> list = Arrays.asList( itemTaxes1, itemTaxes2, itemTaxes3, itemTaxes4 );
        Totals totals = taxCalculator.calcTotals( list );
        assertEquals( 6.7, totals.getTotalTaxes(), Double.MIN_NORMAL );
        assertEquals( 74.68, totals.getTotalSum(), Double.MIN_NORMAL );
        taxCalculator.printResult( list, totals );
        System.out.println( "\n" );
    }
}
