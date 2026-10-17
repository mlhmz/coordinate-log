package xyz.mlhmz.mcserverinformation.coordinatelog;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import xyz.mlhmz.mcserverinformation.coordinatelog.utils.LocationUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class LocationUtilTest {
    @ParameterizedTest
    @CsvSource(value = {"100,100", "-100,-100", "0,0"})
    void testParseBlockCoordinate_WithPositiveAndNegativeIntegers_ResultsInParsedResult(String coordinateValue, int intValue) {
        Player playerMock = mock(Player.class);

        Integer coordinate = LocationUtil.parseBlockCoordinate(playerMock, coordinateValue, "X");

        assertThat(coordinate).isNotNull();
        assertThat(coordinate).isEqualTo(intValue);
    }

    @Test
    void testParseBlockCoordinate_WithCurrentLocation_ResultsInWorldLocation() {
        int xCoordinate = 100;
        int yCoordinate = 75;
        int zCoordinate = 150;
        Player playerMock = mock(Player.class);
        Location locationMock = mock(Location.class);

        doReturn(locationMock).when(playerMock).getLocation();
        doReturn(xCoordinate).when(locationMock).getBlockX();
        doReturn(yCoordinate).when(locationMock).getBlockY();
        doReturn(zCoordinate).when(locationMock).getBlockZ();

        Integer parsedXCoordinate = LocationUtil.parseBlockCoordinate(playerMock, "~", "X");
        Integer parsedYCoordinate = LocationUtil.parseBlockCoordinate(playerMock, "~", "Y");
        Integer parsedZCoordinate = LocationUtil.parseBlockCoordinate(playerMock, "~", "Z");

        assertThat(parsedXCoordinate).isNotNull();
        assertThat(parsedYCoordinate).isNotNull();
        assertThat(parsedZCoordinate).isNotNull();
        assertThat(parsedXCoordinate).isEqualTo(xCoordinate);
        assertThat(parsedYCoordinate).isEqualTo(yCoordinate);
        assertThat(parsedZCoordinate).isEqualTo(zCoordinate);
    }

    @Test
    void testParseBlockCoordinate_WithInvalidValue_ResultsInNull() {
        Player playerMock = mock(Player.class);

        Integer coordinate = LocationUtil.parseBlockCoordinate(playerMock, "INVALID", "X");

        assertThat(coordinate).isNull();
    }
}