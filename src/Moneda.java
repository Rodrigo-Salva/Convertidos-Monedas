import java.math.BigDecimal;

public record Moneda(String base_code,
                     String target_code,
                     BigDecimal conversion_rate,
                     BigDecimal conversion_result) {
}
