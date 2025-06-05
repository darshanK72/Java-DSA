/*-
 * LeetCode 355. Design Twitter
 * 
 * Problem Statement:
 *     Design a simplified version of Twitter where users can post tweets, follow/unfollow
 *     other users, and see the 10 most recent tweets in their news feed. Each tweet must
 *     be posted by a user who exists in the system.
 * 
 * Input:
 *     - postTweet(userId, tweetId): userId (1 <= userId <= 500), tweetId (1 <= tweetId <= 10^4)
 *     - follow(followerId, followeeId): followerId, followeeId (1 <= id <= 500)
 *     - unfollow(followerId, followeeId): followerId, followeeId (1 <= id <= 500)
 *     - getNewsFeed(userId): userId (1 <= userId <= 500)
 * 
 * Output:
 *     - getNewsFeed: List of tweet IDs in chronological order (most recent first)
 *     - Other methods: void
 * 
 * Example:
 *     Input: 
 *         twitter = new Twitter();
 *         twitter.postTweet(1, 5);
 *         twitter.getNewsFeed(1);  // return [5]
 *         twitter.follow(1, 2);
 *         twitter.postTweet(2, 6);
 *         twitter.getNewsFeed(1);  // return [6, 5]
 * 
 *     Explanation:
 *         User 1 posts tweet 5
 *         User 1's news feed shows tweet 5
 *         User 1 follows user 2
 *         User 2 posts tweet 6
 *         User 1's news feed shows tweets 6 and 5 (most recent first)
 */

import java.util.*;

public class j15DesignTwitter {

    /*-
     * Approach: Object-Oriented Design with Priority Queue
     * 
     * Intuition:
     * - Use a linked list to store tweets for each user (most recent first)
     * - Use a priority queue to merge tweets from all followed users
     * - Use a timestamp to track tweet order
     * - Use a map to store user information
     * 
     * Explanation:
     * - Step 1: Create Tweet class to store tweet information and link to next tweet
     * - Step 2: Create User class to manage following relationships and tweet list
     * - Step 3: Use PriorityQueue to merge tweets from all followed users
     * - Step 4: Implement follow/unfollow operations using HashSet
     * 
     * Time Complexity:
     * - postTweet: O(1)
     * - getNewsFeed: O(n log k) where n is number of tweets, k is number of users
     * - follow/unfollow: O(1)
     * 
     * Space Complexity: O(n + m) where n is number of users and m is number of tweets
     */

    static class Twitter {
        private static int timestamp = 0;
        
        /*-
         * Tweet class to store tweet information
         * - id: unique tweet identifier
         * - time: timestamp of tweet creation
         * - next: pointer to next tweet in user's tweet list
         */
        static class Tweet {
            int id;
            int time;
            Tweet next;
            
            Tweet(int id) {
                this.id = id;
                this.time = timestamp++;
                this.next = null;
            }
        }
        
        /*-
         * User class to manage user information
         * - id: unique user identifier
         * - following: set of user IDs being followed
         * - tweetHead: pointer to most recent tweet
         */
        static class User {
            int id;
            Set<Integer> following;
            Tweet tweetHead;
            
            User(int id) {
                this.id = id;
                this.following = new HashSet<>();
                this.tweetHead = null;
                // User follows themselves
                follow(id);
            }
            
            void follow(int followeeId) {
                following.add(followeeId);
            }
            
            void unfollow(int followeeId) {
                following.remove(followeeId);
            }
            
            void post(int tweetId) {
                Tweet tweet = new Tweet(tweetId);
                tweet.next = tweetHead;
                tweetHead = tweet;
            }
        }
        
        private Map<Integer, User> userMap;
        
        public Twitter() {
            userMap = new HashMap<>();
        }
        
        /**
         * Posts a tweet for the given user
         * @param userId    ID of the user posting the tweet
         * @param tweetId   ID of the tweet to post
         */
        public void postTweet(int userId, int tweetId) {
            userMap.putIfAbsent(userId, new User(userId));
            userMap.get(userId).post(tweetId);
        }
        
        /**
         * Retrieves the 10 most recent tweets in the user's news feed
         * @param userId    ID of the user whose news feed to retrieve
         * @return         List of tweet IDs in chronological order
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            if (!userMap.containsKey(userId)) return res;
            
            Set<Integer> following = userMap.get(userId).following;
            PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);
            
            // Add tweets from all followed users to the priority queue
            for (int followeeId : following) {
                User user = userMap.get(followeeId);
                if (user.tweetHead != null) {
                    pq.offer(user.tweetHead);
                }
            }
            
            // Get top 10 tweets
            int count = 0;
            while (!pq.isEmpty() && count < 10) {
                Tweet tweet = pq.poll();
                res.add(tweet.id);
                count++;
                
                if (tweet.next != null) {
                    pq.offer(tweet.next);
                }
            }
            
            return res;
        }
        
        /**
         * Makes user follow another user
         * @param followerId    ID of the user who will follow
         * @param followeeId    ID of the user to be followed
         */
        public void follow(int followerId, int followeeId) {
            userMap.putIfAbsent(followerId, new User(followerId));
            userMap.putIfAbsent(followeeId, new User(followeeId));
            userMap.get(followerId).follow(followeeId);
        }
        
        /**
         * Makes user unfollow another user
         * @param followerId    ID of the user who will unfollow
         * @param followeeId    ID of the user to be unfollowed
         */
        public void unfollow(int followerId, int followeeId) {
            if (userMap.containsKey(followerId)) {
                userMap.get(followerId).unfollow(followeeId);
            }
        }
    }
}
