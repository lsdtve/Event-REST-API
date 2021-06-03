package com.infflearn.restapi.restapi.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class EventTest {

    @Test
    void bulider() {
        Event event = Event.builder()
            .name("Event")
            .description("REST API")
            .build();
        assertThat(event).isNotNull();
    }
    
    @Test
    void javaBean() {
        // Given
        String spring = "Srping";
        String name = "Evnet";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(spring);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(spring);
    }

    @DisplayName(value = "이벤트 Free 테스트")
    @ParameterizedTest(name = "이벤트 생성시 basePrice:{0} maxPrice:{1} 경우 {2}확인")
    @CsvSource({"0,0,true", "100,0,false", "0,100,false", "200,500,false"})
    void testFree(int basePrice, int maxPrice, boolean isFree) {
        // Given
        Event event = Event.builder()
            .basePrice(basePrice)
            .maxPrice(maxPrice)
            .build();

        // When
        event.update();

        // Then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    @DisplayName(value = "이벤트 Offline 테스트")
    @ParameterizedTest(name = "location :{0} 경우 {1} 테스트")
    @MethodSource("paramsForTestOffline")
    void testOffline(String location, boolean isOffline) {
        // Given
        Event event = Event.builder()
            .location(location)
            .build();

        // When
        event.update();

        // Then
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private static Stream<Arguments> paramsForTestOffline() {
        return Stream.of(
            Arguments.of("광주", true),
            Arguments.of(null, false),
            Arguments.of("   ", false)
        );
    }

}