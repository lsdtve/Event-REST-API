package com.infflearn.restapi.restapi.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


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

    @Test
    void testFree() {
        Event event = Event.builder()
            .basePrice(0)
            .maxPrice(0)
            .build();

        event.update();

        assertThat(event.isFree()).isTrue();

        event = Event.builder()
            .basePrice(0)
            .maxPrice(100)
            .build();

        event.update();

        assertThat(event.isFree()).isFalse();

        event = Event.builder()
            .basePrice(100)
            .maxPrice(0)
            .build();

        event.update();

        assertThat(event.isFree()).isFalse();
    }

    @Test
    void testOffline() {
        Event event = Event.builder()
            .location("광주")
            .build();

        event.update();

        assertThat(event.isOffline()).isTrue();

        event = Event.builder()
            .build();

        event.update();

        assertThat(event.isOffline()).isFalse();
    }

}