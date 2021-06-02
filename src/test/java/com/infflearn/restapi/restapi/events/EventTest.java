package com.infflearn.restapi.restapi.events;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


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
}