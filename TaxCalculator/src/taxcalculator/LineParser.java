package taxcalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static taxcalculator.GoodsItem.Type.BOOK;
import static taxcalculator.GoodsItem.Type.FOOD;
import static taxcalculator.GoodsItem.Type.MEDICAL_PRODUCT;
import static taxcalculator.GoodsItem.Type.GOOD;

/**
 *
 * @author dpanov
 */
public class LineParser
{
    private final BufferedReader reader;
    
    private final Pattern LINE_PATTERN = Pattern.compile( "(\\d*)\\s*(.*)\\s*(at\\s*([0-9\\.]*))$" );

    public LineParser( BufferedReader reader )
    {
        this.reader = reader;
    }
        
    public GoodsItem parseNext()
    {
        GoodsItem retn = null;
        String line = null;
        try{
            line = reader.readLine();
        } catch ( IOException ex )
        {
            throw new RuntimeException(ex);
        }
        if ( line == null )
            return null;
        Matcher matcher = LINE_PATTERN.matcher( line );
        if ( matcher.matches() )
        {
            String amount = matcher.group( 1 );
            String name = matcher.group( 2 );
            String price = matcher.group( 4 );
            retn = createGoodsItem( amount, name, price );
        }else{
            throw new RuntimeException( "Wrong item format exception: " + line );
        }
        
        return retn;
    }
    
    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    protected GoodsItem createGoodsItem( String amount, String name, String price )
    {
        double amountDouble = Double.parseDouble( amount );
        name = (name == null) ? "" : name.trim();
        double priceDouble = Double.parseDouble( price );

        boolean imported = name.toLowerCase().contains( "imported" );
        
        GoodsItem.Type type = GOOD;
        if ( name.contains( "book" ) )
            type = BOOK;
        else if ( name.contains( "chocolate" ) )
            type = FOOD;
        else if ( name.contains( "pills" ) )
            type = MEDICAL_PRODUCT;
        
        
        return new GoodsItem( name, priceDouble, amountDouble, type, imported );
    }
    
}
