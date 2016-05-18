/*
 * \ * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */

package org.springframework.social.alfresco.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.synchronization.api.GetChangesResponse;
import org.alfresco.service.synchronization.api.StartSyncRequest;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CmisVersion;
import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.AlfrescoList;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.Favourite;
import org.springframework.social.alfresco.api.entities.LegacyPerson;
import org.springframework.social.alfresco.api.entities.LegacySite;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Metadata;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Site.Visibility;
import org.springframework.social.alfresco.api.entities.SiteMembershipRequest;
import org.springframework.social.alfresco.api.entities.StartSyncResponse;
import org.springframework.social.alfresco.api.entities.Subscriber;
import org.springframework.social.alfresco.api.entities.Subscription;
import org.springframework.social.alfresco.api.entities.SubscriptionType;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.api.entities.UserActivationResponse;
import org.springframework.social.alfresco.api.entities.UserRegistration;
import org.springframework.web.client.RestClientException;

/**
 * 
 * @author jottley
 * @author sglover
 * 
 */
public interface Alfresco
{
    public static final String DEFAULT_SCOPE = "public_api";
    public static CMISEndpoint cmisAtom10Endpoint = new CMISEndpoint(BindingType.ATOMPUB, CmisVersion.CMIS_1_0);
    public static CMISEndpoint cmisAtom11Endpoint = new CMISEndpoint(BindingType.ATOMPUB, CmisVersion.CMIS_1_0);
    public static CMISEndpoint cmisBrowser11Endpoint = new CMISEndpoint(BindingType.BROWSER, CmisVersion.CMIS_1_1);

    /**
     * Additional Query Parameters
     * 
     * @author jottley
     */
    public static class QueryParams
    {
        /**
         * Query Properties Parameter
         */
        public final static String PROPERTIES = "properties";
    }

    /**
     * Template Parmater Constant names
     * 
     * Url:
     * https://api.alfresco.com/{network}/public/alfresco/versions/1/networks/{
     * network}
     * 
     * @author jottley
     */
    public static class TemplateParams
    {
        /**
         * Network Parameter
         */
        public final static String NETWORK = "network";
        /**
         * Site Parameter
         */
        public final static String SITE = "site";
        /**
         * Container Parameter
         */
        public final static String CONTAINER = "container";
        /**
         * Preference Parameter
         */
        public final static String PREFERENCE = "preference";
        /**
         * Tag Parameter
         */
        public final static String TAG = "tag";
        /**
         * Rating Parameter
         */
        public final static String RATING = "rating";
        /**
         * Comment Parameter
         */
        public final static String COMMENT = "comment";
        /**
         * Node Parameter
         */
        public final static String NODE = "node";
        /**
         * Person Parameter
         */
        public final static String PERSON = "person";
        /**
         * Member Parameter
         */
        public final static String MEMBER = "member";
    }

    public java.util.List<Repository> getCMISNetworks();

    public Session getCMISSession(String networkId);

    public Session getCMISSession(String networkId, CMISEndpoint cmisEndpoint);

    /**
     * Get the Alfresco Network for the user
     * 
     * @param network (String)
     * 
     */
    public Network getNetwork(String network)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all Alfresco Networks that the user is a member of
     */
    public AlfrescoList<Network> getNetworks()
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all Alfresco Networks that the user is a member of filter by
     * parameters
     * 
     * @param parameters ((Map<String, String>)
     * 
     */
    public AlfrescoList<Network> getNetworks(Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get site in network
     * 
     * @param site (String)
     * @param network (String)
     *
     */
    public Site getSite(String site, String network)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all sites in network
     */
    public AlfrescoList<Site> getSites(String network)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all sites in network filtered by parameters
     */
    public AlfrescoList<Site> getSites(String network, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get Container in site in network
     */
    public Container getContainer(String network, String site, String contatiner)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all containers in site in network
     */
    public AlfrescoList<Container> getContainers(String network, String site)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all containers in site in network filtered by parameters
     */
    public AlfrescoList<Container> getContainers(String network, String site, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get member of site in network
     */
    public Member getMember(String network, String site, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all members of site in network
     */
    public AlfrescoList<Member> getMembers(String network, String site)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all members of site in network filtered by parameters
     */
    public AlfrescoList<Member> getMembers(String network, String site, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Add member to site in network with role
     * @param personId
     *        - person must exist in network
     * @param role
     *        - must be one Role.SiteManager, Role.SiteContributor,
     *        Role.SiteCollaborator, Role.SiteConsumer
     */
    public Member addMember(String network, String site, String personId, Role role)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Update Role of member of site in network
     * @param personId
     *        - person must be member of site
     * @param role
     *        - must be one Role.SiteManager, Role.SiteContributor,
     *        Role.SiteCollaborator, Role.SiteConsumer
     */
    public void updateMember(String network, String site, String personId, Role role)
            throws RestClientException;

    /**
     * Delete person membership from site in network
     */
    public void deleteMember(String network, String site, String personId)
            throws RestClientException;

    LegacyPerson createPerson(String network, String username, String firstName, String lastName, String email,
            String password)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get person from network
     */
    public Person getPerson(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get sites in network that the user is a member of
     */
    public AlfrescoList<Site> getSites(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get sites in network that the user is a member of filtered by parameters
     */
    public AlfrescoList<Site> getSites(String network, String person, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get site in network that the person is a member of
     */
    public Site getSite(String network, String person, String site)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public Site addFavoriteSite(String network, String personId, String siteId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get favorite sites of person in network
     */
    public AlfrescoList<Site> getFavoriteSites(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get favorite sites of person in network filtered by parameters
     */
    public AlfrescoList<Site> getFavoriteSites(String network, String person, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get preference for person in network
     */
    public Preference getPreference(String network, String person, String preference)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all preferences for person in network
     */
    public AlfrescoList<Preference> getPreferences(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all preferences for person in network filtered by parameters
     */
    public AlfrescoList<Preference> getPreferences(String network, String person, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get network for person
     */
    public Network getNetwork(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all networks for person
     */
    public AlfrescoList<Network> getNetworks(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all networks for person filtered by parameters
     */
    public AlfrescoList<Network> getNetworks(String network, String person, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get activities for person in network
     */
    public AlfrescoList<Activity> getActivities(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get activities for person in network filtered by parameters
     */
    public AlfrescoList<Activity> getActivities(String network, String person, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get tag in network
     */
    public Tag getTag(String network, String tag)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get all tags in network
     */
    public AlfrescoList<Tag> getTags(String network)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public AlfrescoList<Tag> getTags(String network, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Update tag in network (rename tag)
     * @param tagId
     *        - Existing tag
     * @param tag
     *        - new tag name
     */
    public void updateTag(String network, String tagId, String tag)
            throws RestClientException;

    /**
     * Get Comments for node in network
     */
    public AlfrescoList<Comment> getComments(String network, String node)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get comments for node in network filtered by parameters
     * 
     */
    public AlfrescoList<Comment> getComments(String network, String node, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Create comment on node in network
     */
    public Comment createComment(String network, String node, String comment)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Create multiple comments on node in network
     */
    public AlfrescoList<Comment> createComments(String network, String node, List<String> comments)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Update comment on node in network
     * @param commentId
     *        - existing commentId
     * @param comment
     *        - updated comment
     */
    public void updateComment(String network, String node, String commentId, String comment)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Delete comment from node in network
     */
    public void deleteComment(String network, String node, String commentId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get tags on node in network
     */
    public AlfrescoList<Tag> getNodesTags(String network, String node)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get tags on node in network filtered by parameters
     */
    public AlfrescoList<Tag> getNodesTags(String network, String node, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Add tag to node in network
     * @param tag
     *        - tag name
     */
    public Tag addTagToNode(String network, String node, String tag)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Add multiple tags to node
     */
    public AlfrescoList<Tag> addTagsToNode(String network, String node, List<String> tags)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Remove Tag from node in network
     */
    public void removeTagFromNode(String network, String node, String tagId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get ratings for node in network
     */
    public AlfrescoList<Rating> getNodeRatings(String network, String node)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get ratings for node in network filtered by parameters
     */
    public AlfrescoList<Rating> getNodeRatings(String network, String node, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get rating for node in network
     * @param rating
     *        - Rating must be of type Rating.STARS or Rating.LIKES
     */
    public Rating getNodeRating(String network, String node, String rating)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Remove rating from node in network
     */
    public void removeNodeRating(String network, String node, String ratingId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Like node
     */
    public Rating rateNode(String network, String node, boolean like)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Rate node (Stars)
     * @param stars
     *        - must be 1 - 5 stars
     */
    public Rating rateNode(String network, String node, int stars)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get home network for user
     */
    public Network getHomeNetwork()
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get the current users profile
     */
    public Person getCurrentUser()
            throws JsonParseException,
            JsonMappingException,
            IOException;

    /**
     * Get the HTTP OPTIONS for the network
     */
    @Deprecated
    public AlfrescoList<Metadata> networkOptions(String network)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public UserRegistration registerUser(String email, String firstName, String lastName, String password,
            String source, String sourceUrl)
            throws IOException;

    public UserActivationResponse activateUser(String id, String key, String email, String firstName, String lastName,
            String password)
            throws IOException;

    public LegacySite createSite(String network, String siteId, String sitePreset, String title, String description,
            Visibility visibility)
            throws IOException;

    void removeSite(String networkId, String siteId);

    public ItemIterable<CmisObject> getChildren(String networkId, String folderId, int skipCount, int maxItems,
            IncludeRelationships includeRelationships,
            Boolean includeAcls, Set<String> propertyFilter, Boolean includePolicies);

    public java.util.List<Tree<FileableCmisObject>> getDescendants(String networkId, String folderId, Integer depth,
            IncludeRelationships includeRelationships,
            Boolean includeAcls, Set<String> propertyFilter, Boolean includePolicies);

    public AlfrescoList<Favourite> getFavorites(String network, String person)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public Favourite getFavorite(String network, String person, String targetGuid)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public AlfrescoList<Favourite> getFavorites(String network, String person, Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public Favourite addFavorite(String network, String personId, Favourite favourite)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public void removeFavourite(String network, String personId, String favouriteId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public AlfrescoList<SiteMembershipRequest> getPersonSiteMembershipRequests(String network, String personId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public AlfrescoList<SiteMembershipRequest> getPersonSiteMembershipRequests(String network, String person,
            Map<String, String> parameters)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public SiteMembershipRequest createPersonSiteMembershipRequest(String network, String personId,
            SiteMembershipRequest siteMembershipRequest)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    public void cancelPersonSiteMembershipRequest(String network, String personId, String siteId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    Subscriber createSubscriber(String networkId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    Subscription createSubscription(String network, String subscriberId, SubscriptionType subscriptionType,
            String targetPath)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    void removeSubscriber(String network, String subscriberId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    void removeSubscription(String network, String subscriberId, String subscriptionId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    StartSyncResponse start(StartSyncRequest req, String networkId, String subscriberId, String subscriptionsQuery)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    GetChangesResponse getSync(String networkId, String subscriberId, String subscriptionsQuery, String syncId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    void endSync(String networkId, String subscriberId, String subscriptionsQuery, String syncId)
            throws JsonParseException,
            JsonMappingException,
            IOException;

    void setCmisOperationContext(OperationContext cmisOperationContext);
}
