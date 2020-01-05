package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.rest.service.inter.IStatusService;

public class StatusResource {

    private IStatusService statusService;

    public StatusResource(IStatusService statusService) {
        this.statusService = statusService;
    }
}
