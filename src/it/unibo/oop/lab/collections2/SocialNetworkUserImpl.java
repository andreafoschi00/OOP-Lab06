package it.unibo.oop.lab.collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * Instructions
 * 
 * This will be an implementation of
 * {@link it.unibo.oop.lab.collections2.SocialNetworkUser}:
 * 
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U>
 *            Specific user type
 */

public class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

	private final Map<String, Set<U>> friends;
    /**
     * Builds a new {@link SocialNetworkUserImpl}.
     * 
     * @param name
     *            the user firstname
     * @param surname
     *            the user lastname
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    public SocialNetworkUserImpl(final String firstName, final String lastName, final String username) {
        this(firstName, lastName, username, -1);

    }

	public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
        this.friends = new HashMap<>();
    }

    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        Set<U> closeFriends = this.friends.get(circle);
        if (closeFriends == null) {
            closeFriends = new HashSet<>();
            this.friends.put(circle, closeFriends);
        }
        return closeFriends.add(user);
    }

    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
    	final Collection<U> usersInGroups = this.friends.get(groupName);
        if (usersInGroups != null) {
            return new ArrayList<>(usersInGroups);
        }
        return Collections.emptyList();
    }

    @Override
    public List<U> getFollowedUsers() {
    	final Set<U> followingUsers = new HashSet<>();
        for (final Set<U> group : friends.values()) {
            followingUsers.addAll(group);
        }
        return new ArrayList<>(followingUsers);
    }

}
