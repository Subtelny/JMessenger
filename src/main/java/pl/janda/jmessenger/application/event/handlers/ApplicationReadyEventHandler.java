package pl.janda.jmessenger.application.event.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.infrastructure.event.EventNotifier;

@Component
@RequiredArgsConstructor
public class ApplicationReadyEventHandler {

    private final EventNotifier eventNotifier;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        eventNotifier.notifyEvents();
    }

}
