package taxcalculator;

/**
 *
 * @author dpanov
 */
public class Totals
{
    private double totalTaxes = 0;
    private double totalSum = 0;

    public void add( ItemTaxes itemTaxes )
    {
        totalTaxes += itemTaxes.getTaxValue();
        totalTaxes = Math.round(100.0*totalTaxes) / 100.0;
        totalSum += itemTaxes.getNetPrice();
        totalSum = Math.round(100.0*totalSum) / 100.0;
    }

    public double getTotalTaxes()
    {
        return totalTaxes;
    }

    public double getTotalSum()
    {
        return totalSum;
    }
        
}
