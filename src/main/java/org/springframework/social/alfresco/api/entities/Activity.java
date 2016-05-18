/*
 * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */
package org.springframework.social.alfresco.api.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.alfresco.api.entities.exceptions.UnknownActivityTypeException;
import org.springframework.social.alfresco.api.entities.exceptions.UnknownRoleException;

public class Activity
{
    /**
     * Activity Query Parameter. The id of a specific site. Specifying this parameter filters the returned collection to include
     * just those activities for the specific site.
     */
    public static final String SITEID = "siteId";
    /**
     * Acitivity Query Parameter. Specifying a value of me filters the returned collection to include just those activities for the
     * specified user. Specifying a value of others filters the returned collection to include just those activities that are not
     * for the specified user.
     */
    public static final String WHO    = "who";


    /**
     * Activity Query WHO Values
     * 
     * @author jottley
     * 
     */
    public static enum Who
    {
        me, others
    };


    private String          postPersonId;
    private String          id;
    private String          siteId;
    private String          networkId;
    private String          feedPersonId;
    private ActivitySummary activitySummary;
    private String          activityType;
    private Date            postedAt;


    /**
     * @return The id of the person who performed the activity
     */
    public String getPostPersonId()
    {
        return postPersonId;
    }


    /**
     * Set the id of the person who performed the activity
     * 
     * @param postPersonId
     */
    public void setPostPersonId(String postPersonId)
    {
        this.postPersonId = postPersonId;
    }


    /**
     * @return The unique id of the activity
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the unique id of the activity
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * @return The unique id of the site on which the activity was performed
     */
    public String getSiteId()
    {
        return siteId;
    }


    /**
     * Set the unique id of the site on which the activity was performed
     * 
     * @param siteId
     */
    public void setSiteId(String siteId)
    {
        this.siteId = siteId;
    }


    /**
     * @return The unique id of the network on which the activity was performed
     */
    public String getNetworkId()
    {
        return networkId;
    }


    /**
     * Set the unique id of the network on which the activity was performed
     * 
     * @param networkId
     */
    public void setNetworkId(String networkId)
    {
        this.networkId = networkId;
    }


    /**
     * @return The feed on which this activity was posted
     */
    public String getFeedPersonId()
    {
        return feedPersonId;
    }


    /**
     * Set the feed on which this activity was posted
     * 
     * @param feedPersonId
     */
    public void setFeedPersonId(String feedPersonId)
    {
        this.feedPersonId = feedPersonId;
    }


    /**
     * @return An object summarizing the activity
     */
    public ActivitySummary getActivitySummary()
    {
        return activitySummary;
    }


    /**
     * Set the object summarizing the activity
     * 
     * @param activitySummary
     */
    public void setActivitySummary(ActivitySummary activitySummary)
    {
        this.activitySummary = activitySummary;
    }


    /**
     * @return The type of activity.
     * 
     * The following are the possible values:
     * <ul>
     *  <li>org.alfresco.blog.post-created </li>
     *  <li>org.alfresco.blog.post-updated </li>
     *  <li>org.alfresco.blog.post-deleted </li>
     *  <li>org.alfresco.comments.comment-created</li>
     *  <li>org.alfresco.comments.comment-updated</li>
     *  <li>org.alfresco.comments.comment-deleted</li>
     *  <li>org.alfresco.discussions.post-created </li>
     *  <li>org.alfresco.discussions.post-updated </li>
     *  <li>org.alfresco.discussions.post-deleted </li>
     *  <li>org.alfresco.discussions.reply-created </li>
     *  <li>org.alfresco.discussions.reply-updated </li>
     *  <li>org.alfresco.calendar.event-created </li>
     *  <li>org.alfresco.calendar.event-updated </li>
     *  <li>org.alfresco.calendar.event-deleted </li>
     *  <li>org.alfresco.documentlibrary.files-added</li>
     *  <li>org.alfresco.documentlibrary.files-updated </li>
     *  <li>org.alfresco.documentlibrary.files-deleted </li>
     *  <li>org.alfresco.documentlibrary.file-added </li>
     *  <li>org.alfresco.documentlibrary.file-updated </li>
     *  <li>org.alfresco.documentlibrary.file-created </li>
     *  <li>org.alfresco.documentlibrary.file-deleted </li>
     *  <li>org.alfresco.documentlibrary.file-liked </li>
     *  <li>org.alfresco.documentlibrary.inline-edit </li>
     *  <li>org.alfresco.documentlibrary.folder-liked </li>
     *  <li>org.alfresco.documentlibrary.folder-added </li>
     *  <li>org.alfresco.documentlibrary.folder-deleted </li>
     *  <li>org.alfresco.documentlibrary.folders-added </li>
     *  <li>org.alfresco.documentlibrary.folders-deleted </li>
     *  <li>org.alfresco.documentlibrary.inline-edit </li>
     *  <li>org.alfresco.documentlibrary.file-previewed </li>
     *  <li>org.alfresco.documentlibrary.file-downloaded </li>
     *  <li>org.alfresco.documentlibrary.file-liked </li>
     *  <li>org.alfresco.documentlibrary.folder-liked </li>
     *  <li>org.alfresco.wiki.page-created </li>
     *  <li>org.alfresco.wiki.page-edited </li>
     *  <li>org.alfresco.wiki.page-renamed </li>
     *  <li>org.alfresco.wiki.page-deleted </li>
     *  <li>org.alfresco.site.user-joined </li>
     *  <li>org.alfresco.site.user-left </li>
     *  <li>org.alfresco.site.user-role-changed </li>
     *  <li>org.alfresco.site.group-added </li>
     *  <li>org.alfresco.site.group-removed </li>
     *  <li>org.alfresco.site.group-role-changed </li>
     *  <li>org.alfresco.site.liked </li>
     *  <li>org.alfresco.links.link-created </li>
     *  <li>org.alfresco.links.link-updated </li>
     *  <li>org.alfresco.links.link-deleted </li>
     *  <li>org.alfresco.datalists.list-created </li>
     *  <li>org.alfresco.datalists.list-updated </li>
     *  <li>org.alfresco.datalists.list-deleted </li>
     *  <li>org.alfresco.subscriptions.followed </li>
     *  <li>org.alfresco.subscriptions.subscribed</li>
     *  <li>org.alfresco.profile.status-changed </li>
     *  </ul>
     */
    public String getActivityType()
    {
        return activityType;
    }


    /**
     * <h3>Set the Activity type</h3>
     * 
     * The following are the possible values:
     * <ul>
     *  <li>org.alfresco.blog.post-created </li>
     *  <li>org.alfresco.blog.post-updated </li>
     *  <li>org.alfresco.blog.post-deleted </li>
     *  <li>org.alfresco.comments.comment-created</li>
     *  <li>org.alfresco.comments.comment-updated</li>
     *  <li>org.alfresco.comments.comment-deleted</li>
     *  <li>org.alfresco.discussions.post-created </li>
     *  <li>org.alfresco.discussions.post-updated </li>
     *  <li>org.alfresco.discussions.post-deleted </li>
     *  <li>org.alfresco.discussions.reply-created </li>
     *  <li>org.alfresco.discussions.reply-updated </li>
     *  <li>org.alfresco.calendar.event-created </li>
     *  <li>org.alfresco.calendar.event-updated </li>
     *  <li>org.alfresco.calendar.event-deleted </li>
     *  <li>org.alfresco.documentlibrary.files-added</li>
     *  <li>org.alfresco.documentlibrary.files-updated </li>
     *  <li>org.alfresco.documentlibrary.files-deleted </li>
     *  <li>org.alfresco.documentlibrary.file-added </li>
     *  <li>org.alfresco.documentlibrary.file-updated </li>
     *  <li>org.alfresco.documentlibrary.file-created </li>
     *  <li>org.alfresco.documentlibrary.file-deleted </li>
     *  <li>org.alfresco.documentlibrary.file-liked </li>
     *  <li>org.alfresco.documentlibrary.inline-edit </li>
     *  <li>org.alfresco.documentlibrary.folder-liked </li>
     *  <li>org.alfresco.documentlibrary.folder-added </li>
     *  <li>org.alfresco.documentlibrary.folder-deleted </li>
     *  <li>org.alfresco.documentlibrary.folders-added </li>
     *  <li>org.alfresco.documentlibrary.folders-deleted </li>
     *  <li>org.alfresco.documentlibrary.inline-edit </li>
     *  <li>org.alfresco.documentlibrary.file-previewed </li>
     *  <li>org.alfresco.documentlibrary.file-downloaded </li>
     *  <li>org.alfresco.documentlibrary.file-liked </li>
     *  <li>org.alfresco.documentlibrary.folder-liked </li>
     *  <li>org.alfresco.wiki.page-created </li>
     *  <li>org.alfresco.wiki.page-edited </li>
     *  <li>org.alfresco.wiki.page-renamed </li>
     *  <li>org.alfresco.wiki.page-deleted </li>
     *  <li>org.alfresco.site.user-joined </li>
     *  <li>org.alfresco.site.user-left </li>
     *  <li>org.alfresco.site.user-role-changed </li>
     *  <li>org.alfresco.site.group-added </li>
     *  <li>org.alfresco.site.group-removed </li>
     *  <li>org.alfresco.site.group-role-changed </li>
     *  <li>org.alfresco.site.liked </li>
     *  <li>org.alfresco.links.link-created </li>
     *  <li>org.alfresco.links.link-updated </li>
     *  <li>org.alfresco.links.link-deleted </li>
     *  <li>org.alfresco.datalists.list-created </li>
     *  <li>org.alfresco.datalists.list-updated </li>
     *  <li>org.alfresco.datalists.list-deleted </li>
     *  <li>org.alfresco.subscriptions.followed </li>
     *  <li>org.alfresco.subscriptions.subscribed</li>
     *  <li>org.alfresco.profile.status-changed </li>
     *  </ul>
     * 
     * @param activityType
     */
    public void setActivityType(String activityType)
    {
//        if (!validateActivityType(activityType))
//        {
//            throw new UnknownActivityTypeException(activityType);
//        }
        this.activityType = activityType;
    }


    /**
     * @return The date time at which the activity was performed
     */
    public Date getPostedAt()
    {
        return postedAt;
    }


    /**
     * Set the date time at which the activity was performed
     * 
     * @param postedAt
     */
    public void setPostedAt(Date postedAt)
    {
        this.postedAt = postedAt;
    }


    /**
     * @author jottley
     * 
     */
    public static class ActivitySummary
    {
        private String firstName;
        private String lastName;
        private String title;
        private String objectId;
        private String parentNodeRef;
        private String memberPersonId;
        private String memberFirstName;
        private String memberLastName;
        private String parentObjectId;
        private String role;


        /**
         * @return The first name of the user performing the activity
         */
        public String getFirstName()
        {
            return firstName;
        }


        /**
         * Set the first name of the user performing the activity
         * 
         * @param firstName
         */
        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }


        /**
         * @return The last name of the user performing the activity
         */
        public String getLastName()
        {
            return lastName;
        }


        /**
         * Set the last name of the user performing the activity
         * 
         * @param lastName
         */
        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }


        /**
         * @return The title of the user performing the activity
         */
        public String getTitle()
        {
            return title;
        }


        public void setTitle(String title)
        {
            this.title = title;
        }


        public String getObjectId()
        {
            return objectId;
        }


        public void setObjectId(String objectId)
        {
            this.objectId = objectId;
        }


        public String getParentObjectId()
        {
            return parentObjectId;
        }


        public void setParentObjectId(String parentObjectId)
        {
            this.parentObjectId = parentObjectId;
        }


        public String getMemberPersonId()
        {
            return memberPersonId;
        }


        public void setMemberPersonId(String memberPersonId)
        {
            this.memberPersonId = memberPersonId;
        }


        public String getMemberFirstName()
        {
            return memberFirstName;
        }


        public void setMemberFirstName(String memberFirstName)
        {
            this.memberFirstName = memberFirstName;
        }


        public String getMemberLastName()
        {
            return memberLastName;
        }


        public void setMemberLastName(String memberLastName)
        {
            this.memberLastName = memberLastName;
        }


        public Role getRole()
        {
            if (this.role != null)
            {
                return Role.valueOf(role);
            }
            else
            {
                return null;
            }

        }


        public void setRole(String role)
        {
            if (!validateRole(role))
            {
                throw new UnknownRoleException(role);
            }
            this.role = role;

        }
    }


    // TODO need to add to setActivityType
    private static boolean validateActivityType(String activityType)
    {
        List<String> activityTypes = new ArrayList<String>(22);
        activityTypes.add("org.alfresco.comments.comment-created");
        activityTypes.add("org.alfresco.comments.comment-updated");
        activityTypes.add("org.alfresco.comments.comment-deleted");
        activityTypes.add("org.alfresco.documentlibrary.files-added");
        activityTypes.add("org.alfresco.documentlibrary.files-updated");
        activityTypes.add("org.alfresco.documentlibrary.files-deleted");
        activityTypes.add("org.alfresco.documentlibrary.file-added");
        activityTypes.add("org.alfresco.documentlibrary.file-created");
        activityTypes.add("org.alfresco.documentlibrary.file-deleted");
        activityTypes.add("org.alfresco.documentlibrary.file-liked");
        activityTypes.add("org.alfresco.documentlibrary.file-updated");
        activityTypes.add("org.alfresco.documentlibrary.inline-edit");
        activityTypes.add("org.alfresco.documentlibrary.folder-liked");
        activityTypes.add("org.alfresco.site.user-joined");
        activityTypes.add("org.alfresco.site.user-left");
        activityTypes.add("org.alfresco.site.user-role-changed");
        activityTypes.add("org.alfresco.site.group-added");
        activityTypes.add("org.alfresco.site.group-removed");
        activityTypes.add("org.alfresco.site.group-role-changed");
        activityTypes.add("org.alfresco.discussions.reply-created");
        activityTypes.add("org.alfresco.subscriptions.followed");
        activityTypes.add("org.alfresco.subscriptions.subscribed");

        return activityTypes.contains(activityType);
    }


    private static boolean validateRole(String role)
    {
        List<String> roles = new ArrayList<String>(5);
        roles.add("");
        roles.add("SiteContributor");
        roles.add("SiteManager");
        roles.add("SiteCollaborator");
        roles.add("SiteConsumer");

        return roles.contains(role);
    }
}
