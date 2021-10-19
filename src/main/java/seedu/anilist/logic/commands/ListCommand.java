package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.function.Predicate;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.anime.StatusEqualsPredicate;
import seedu.anilist.ui.TabOption;

/**
 * Lists all animes in the anime list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all animes. An optional status parameter can be"
            + "provided and only animes with that status will be listed.\n"
            + "Parameters: [" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + PREFIX_STATUS + "watching\n";
    private final Predicate<Anime> predicate;
    private final Status statusToMatch;

    /**
     * Constructor for ListCommand. Sets predicate for filtered list
     * and sets the statusToMatch to change tabs.
     */
    public ListCommand(Predicate<Anime> predicate) {
        this.predicate = predicate;
        if (predicate instanceof StatusEqualsPredicate) {
            this.statusToMatch = ((StatusEqualsPredicate) predicate).getStatus();
        } else {
            this.statusToMatch = null;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAnimeList(predicate);
        if (statusToMatch == null) {
            model.setCurrentTab(TabOption.TabOptions.ALL);
        } else if (statusToMatch.toString().equals("towatch")) {
            model.setCurrentTab(TabOption.TabOptions.TOWATCH);
        } else if (statusToMatch.toString().equals("watching")) {
            model.setCurrentTab(TabOption.TabOptions.WATCHING);
        } else if (statusToMatch.toString().equals("finished")) {
            model.setCurrentTab(TabOption.TabOptions.FINISHED);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_ANIME_LISTED_OVERVIEW, model.getFilteredAnimeList().size()));
    }
}
