//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.15
//
package com.amazonaws.mobile;

import android.content.Context;
import android.util.Log;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobile.user.IdentityManager;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.regions.Regions;
import com.amazonaws.mobile.content.UserFileManager;
import com.amazonaws.mobile.content.ContentManager;

/**
 * The AWS Mobile Client bootstraps the application to make calls to AWS 
 * services. It creates clients which can be used to call services backing the
 * features you selected in your project.
 */
public class AWSMobileClient {

    private static final String LOG_TAG = AWSMobileClient.class.getSimpleName();

    private static AWSMobileClient instance;

    private final Context context;

    private ClientConfiguration clientConfiguration;
    private IdentityManager identityManager;
    private PinpointManager pinpointManager;

    /**
     * Build class used to create the AWS mobile client.
     */
    public static class Builder {

        private Context applicationContext;
        private String  cognitoIdentityPoolID;
        private Regions cognitoRegion;
        private String  mobileAnalyticsAppID;
        private ClientConfiguration clientConfiguration;
        private IdentityManager identityManager;

	/**
	 * Constructor.
	 * @param context Android context.
	 */
        public Builder(final Context context) {
            this.applicationContext = context.getApplicationContext();
        };

	/**
	 * Provides the Amazon Cognito Identity Pool ID.
	 * @param cognitoIdentityPoolID identity pool ID
	 * @return builder
	 */
        public Builder withCognitoIdentityPoolID(final String cognitoIdentityPoolID) {
            this.cognitoIdentityPoolID = cognitoIdentityPoolID;
            return this;
        };
        
	/**
	 * Provides the Amazon Cognito service region.
	 * @param cognitoRegion service region
	 * @return builder
	 */
        public Builder withCognitoRegion(final Regions cognitoRegion) {
            this.cognitoRegion = cognitoRegion;
            return this;
        }

        /**
	 * Provides the Amazon Mobile Analytics App ID.
	 * @param mobileAnalyticsAppID application ID
	 * @return builder
	 */
        public Builder withMobileAnalyticsAppID(final String mobileAnalyticsAppID) {
            this.mobileAnalyticsAppID = mobileAnalyticsAppID;
            return this;
        };

        /**
         * Provides the identity manager.
	 * @param identityManager identity manager
	 * @return builder
	 */
        public Builder withIdentityManager(final IdentityManager identityManager) {
            this.identityManager = identityManager;
            return this;
        }

        /**
         * Provides the client configuration
         * @param clientConfiguration client configuration
         * @return builder
         */
        public Builder withClientConfiguration(final ClientConfiguration clientConfiguration) {
            this.clientConfiguration = clientConfiguration;
            return this;
        }

	/**
	 * Creates the AWS mobile client instance and initializes it.
	 * @return AWS mobile client
	 */
        public AWSMobileClient build() {
            return
                new AWSMobileClient(applicationContext,
                                    cognitoIdentityPoolID,
                                    cognitoRegion,
                                    mobileAnalyticsAppID,
                                    identityManager,
                                    clientConfiguration);
        }
    }

    private AWSMobileClient(final Context context,
                            final String  cognitoIdentityPoolID,
                            final Regions cognitoRegion,
                            final String mobileAnalyticsAppID,
                            final IdentityManager identityManager,
                            final ClientConfiguration clientConfiguration) {

        this.context = context;
        this.identityManager = identityManager;
        this.clientConfiguration = clientConfiguration;

        try {
            final PinpointConfiguration config = new PinpointConfiguration(context,
                mobileAnalyticsAppID, AWSConfiguration.AMAZON_MOBILE_ANALYTICS_REGION,
                identityManager.getCredentialsProvider());
            this.pinpointManager = new PinpointManager(config);
        }
        catch (final AmazonClientException ex) {
            Log.e(LOG_TAG, "Unable to initalize Amazon Mobile Analytics. " + ex.getMessage(), ex);
        }

    }

    /**
     * Sets the singleton instance of the AWS mobile client.
     * @param client client instance
     */
    public static void setDefaultMobileClient(AWSMobileClient client) {
        instance = client;
    }

    /**
     * Gets the default singleton instance of the AWS mobile client.
     * @return client
     */
    public static AWSMobileClient defaultMobileClient() {
        return instance;
    }

    /**
     * Gets the identity manager.
     * @return identity manager
     */
    public IdentityManager getIdentityManager() {
        return this.identityManager;
    }

    /**
     * Gets the Amazon Cognito Sync Manager, which is responsible for saving and
     * loading user profile data, such as game state or user settings.
     * @return sync manager
     */
    public CognitoSyncManager getSyncManager() {
        return identityManager.getSyncManager();
    }

    /**
     * Creates and initialize the default AWSMobileClient if it doesn't already
     * exist using configuration constants from {@link AWSConfiguration}.
     *
     * @param context an application context.
     */
    public static void initializeMobileClientIfNecessary(final Context context) {
        if (AWSMobileClient.defaultMobileClient() == null) {
            Log.d(LOG_TAG, "Initializing AWS Mobile Client...");
            final ClientConfiguration clientConfiguration = new ClientConfiguration();
            clientConfiguration.setUserAgent(AWSConfiguration.AWS_MOBILEHUB_USER_AGENT);
            final IdentityManager identityManager = new IdentityManager(context, clientConfiguration);
            final AWSMobileClient awsClient =
                new AWSMobileClient.Builder(context)
                    .withCognitoRegion(AWSConfiguration.AMAZON_COGNITO_REGION)
                    .withCognitoIdentityPoolID(AWSConfiguration.AMAZON_COGNITO_IDENTITY_POOL_ID)
                    .withMobileAnalyticsAppID(AWSConfiguration.AMAZON_MOBILE_ANALYTICS_APP_ID)
                    .withIdentityManager(identityManager)
                    .withClientConfiguration(clientConfiguration)
                    .build();

            AWSMobileClient.setDefaultMobileClient(awsClient);
        }
        Log.d(LOG_TAG, "AWS Mobile Client is OK");
    }

    /**
     * Gets the Amazon Mobile Pinpoint Manager, which allows you to submit
     * custom and monetization events to the Amazon Mobile Analytics system. It
     * also handles recording user session data events. Additionally, it supports
     * mobile targeting using Pinpoint.
     * @return pinpoint manager.
     */
    public PinpointManager getPinpointManager() {
        return this.pinpointManager;
    }

    /**
     * Creates a User File Manager instance, which facilitates file transfers
     * between the device and the specified Amazon S3 (Simple Storage Service) bucket.
     *
     * @param s3Bucket Amazon S3 bucket
     * @param s3FolderPrefix Folder pre-fix for files affected by this user file
     *                       manager instance
     * @param resultHandler handles the resulting UserFileManager instance
     */
    public void createUserFileManager(final String s3Bucket,
                                      final String s3FolderPrefix,
                                      final Regions region,
                                      final UserFileManager.BuilderResultHandler resultHandler) {

        new UserFileManager.Builder().withContext(context)
            .withIdentityManager(getIdentityManager())
            .withS3Bucket(s3Bucket)
            .withS3ObjectDirPrefix(s3FolderPrefix)
            .withLocalBasePath(context.getFilesDir().getAbsolutePath())
            .withClientConfiguration(clientConfiguration)
            .withRegion(region)
            .build(resultHandler);
    }

    /**
     * Creates the default Content Manager, which allows files to be downloaded from
     * the Amazon S3 (Simple Storage Service) bucket associated with the App Content
     * Delivery feature (optionally through Amazon CloudFront if Multi-Region CDN option
     * was selected).
     * @param resultHandler handles the resulting ContentManager instance
     */
    public void createDefaultContentManager(final ContentManager.BuilderResultHandler resultHandler) {
        new ContentManager.Builder()
            .withContext(context)
            .withIdentityManager(identityManager)
            .withS3Bucket(AWSConfiguration.AMAZON_CONTENT_DELIVERY_S3_BUCKET)
            .withLocalBasePath(context.getFilesDir().getAbsolutePath())
            .withRegion(AWSConfiguration.AMAZON_CONTENT_DELIVERY_S3_REGION)
            .withCloudFrontDomainName(AWSConfiguration.AMAZON_CLOUD_FRONT_DISTRIBUTION_DOMAIN)
            .withClientConfiguration(clientConfiguration)
            .build(resultHandler);
    }

}
