package eu.tinylinden;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PingTest {

    @Test
    void should_answer_pong_for_every_ping() {
        // expect
        assertThat(new Ping().ping()).isEqualTo("pong");
    }
}