/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

import org.mdedetrich.apache.sonatype.ApacheSonatypePlugin
import org.mdedetrich.apache.sonatype.ApacheSonatypePlugin.autoImport.*
import sbt.*
import sbt.Keys.*

/**
 * Copies LICENSE and NOTICE files into jar META-INF dir
 */
object AddMetaInfLicenseFiles extends AutoPlugin {

  private lazy val baseDir = LocalRootProject / baseDirectory

  override lazy val projectSettings = Seq(
    apacheSonatypeLicenseFile := baseDir.value / "legal" / "StandardLicense.txt",
    apacheSonatypeNoticeFile := baseDir.value / "legal" / "PekkoKafkaNotice.txt",
    apacheSonatypeDisclaimerFile := Some((LocalRootProject / baseDirectory).value / "DISCLAIMER"))

  /**
   * Settings specific for Pekko Kafka cluster-sharding subproject which require a different license file.
   */
  lazy val clusterShardingSettings = Seq(
    apacheSonatypeLicenseFile := baseDir.value / "LICENSE",
    apacheSonatypeNoticeFile := baseDir.value / "NOTICE")

  override def trigger = allRequirements

  override def requires = ApacheSonatypePlugin

}
