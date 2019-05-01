package fr.esgi.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.service.StatsService;
import fr.esgi.service.dto.StatsDTO;
import io.swagger.annotations.Api;

/**
 * REST controller for managing Stats.
 * @author christopher
 */
@Api(value = "Stats")
@RestController
@RequestMapping("/api")
public class StatsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatsResource.class);

    private final StatsService statsService;

    @Autowired
    public StatsResource(StatsService statsService) {
        this.statsService = statsService;
    }

    /**
     * GET  /stats : Returns stats of subscriptions.
     * @param userId the userId of the statsDTO to retrieve
     * @param categoryId the categoryId of the statsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the stats in body
     */
    @GetMapping(value = "/stats")
    public ResponseEntity<StatsDTO> getStats(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "categoryId") Long categoryId) {
        LOGGER.debug("REST request to get stats: {} {}", userId, categoryId);
        return ResponseEntity.ok()
                .body(statsService.getStatsForTrick(userId, categoryId));
    }

}
