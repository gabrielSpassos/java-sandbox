import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

    public static ZoneId BR_ZONE = ZoneId.of("America/Sao_Paulo");

    public static LocalDateTime now() {
        return LocalDateTime.now(BR_ZONE);
    }

    public static long getDifferenceBetweenDates(LocalDateTime start, LocalDateTime finish) {
        return ChronoUnit.MILLIS.between(start, finish);
    }
}
