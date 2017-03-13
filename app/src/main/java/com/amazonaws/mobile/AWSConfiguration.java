//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.15
//
package com.amazonaws.mobile;

import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {
    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
        "MobileHub 56839547-5d92-4198-b879-f5ef2dbf658c aws-my-sample-app-android-v0.15";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
      Regions.fromName("us-east-1");
    public static final String  AMAZON_COGNITO_IDENTITY_POOL_ID =
        "us-east-1:800a5a13-75f1-4ad9-b6dd-c0f4365b0336";
    // AMAZON MOBILE ANALYTICS
    public static final String  AMAZON_MOBILE_ANALYTICS_APP_ID =
        "a15af095ba154d52a279dd9417b515f3";
    // Amazon Mobile Analytics region
    public static final Regions AMAZON_MOBILE_ANALYTICS_REGION = Regions.US_EAST_1;
    // Google Client ID for Web application
    public static final String GOOGLE_CLIENT_ID =
        "336380704643-a2j0lkij7b1a6ejpcnv55ed9g2i7v84q.apps.googleusercontent.com";
    public static final String AMAZON_CONTENT_DELIVERY_S3_BUCKET =
        "heeloaws-contentdelivery-mobilehub-221694023";
    public static final Regions AMAZON_CONTENT_DELIVERY_S3_REGION =
       Regions.fromName("us-east-1");
    public static final String AMAZON_CLOUD_FRONT_DISTRIBUTION_DOMAIN =
        "djwknh9kiimxt.cloudfront.net";
    // S3 BUCKET
    public static final String AMAZON_S3_USER_FILES_BUCKET =
        "heeloaws-userfiles-mobilehub-221694023";
    // S3 BUCKET REGION
    public static final Regions AMAZON_S3_USER_FILES_BUCKET_REGION =
        Regions.fromName("us-east-1");
    public static final String AMAZON_COGNITO_USER_POOL_ID =
        "us-east-1_YTYL75IMr";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_ID =
        "2f4tkkicn0dedi26jjv6bc3m59";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_SECRET =
        "1a06ctfp6ooi0t1mp66t4c9mje63qeopu4ovotlsbscbqkiqcd7b";
}