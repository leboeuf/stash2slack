package com.pragbits.stash.components;

import com.atlassian.event.api.EventListener;
import com.atlassian.stash.event.pull.PullRequestActivityEvent;
import com.atlassian.stash.nav.NavBuilder;
import com.atlassian.stash.pull.PullRequestService;
import com.atlassian.stash.repository.Repository;
import com.google.gson.Gson;
import com.pragbits.stash.SlackGlobalSettingsService;
import com.pragbits.stash.SlackSettings;
import com.pragbits.stash.SlackSettingsService;
import com.pragbits.stash.models.PullRequestEvent;
import com.pragbits.stash.tools.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PullRequestActivityListener {
    static final String KEY_GLOBAL_SETTING_HOOK_URL = "stash2slack.globalsettings.hookurl";
    private static final Logger log = LoggerFactory.getLogger(PullRequestActivityListener.class);

    private final SlackGlobalSettingsService slackGlobalSettingsService;
    private final SlackSettingsService slackSettingsService;
    private final NavBuilder navBuilder;
    private final SlackNotifier slackNotifier;
    private final PullRequestService pullRequestService;
    private final Gson gson = new Gson();

    public PullRequestActivityListener(SlackGlobalSettingsService slackGlobalSettingsService,
                                       SlackSettingsService slackSettingsService,
                                       NavBuilder navBuilder,
                                       SlackNotifier slackNotifier,
                                       PullRequestService pullRequestService) {
        this.slackGlobalSettingsService = slackGlobalSettingsService;
        this.slackSettingsService = slackSettingsService;
        this.navBuilder = navBuilder;
        this.slackNotifier = slackNotifier;
        this.pullRequestService = pullRequestService;
    }

    @EventListener
    public void NotifySlackChannel(PullRequestActivityEvent event) {
        // find out if notification is enabled for this repo
        Repository repository = event.getPullRequest().getToRef().getRepository();
        SlackSettings slackSettings = slackSettingsService.getSlackSettings(repository);
        String globalHookUrl = slackGlobalSettingsService.getWebHookUrl(KEY_GLOBAL_SETTING_HOOK_URL);

        if (slackSettings.isSlackNotificationsEnabled()) {

            String localHookUrl = slackSettings.getSlackWebHookUrl();
            WebHookSelector hookSelector = new WebHookSelector(globalHookUrl, localHookUrl);

            if (!hookSelector.isHookValid()) {
                log.error("There is no valid configured Web hook url! Reason: " + hookSelector.getProblem());
                return;
            }

            PullRequestEvent eventToSend = new PullRequestEvent(event, navBuilder, pullRequestService);

            String jsonPayload = gson.toJson(eventToSend);

            slackNotifier.SendSlackNotification(hookSelector.getSelectedHook(), jsonPayload);
        }
    }
}
