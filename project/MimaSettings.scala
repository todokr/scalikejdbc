import sbt._, Keys._
import com.typesafe.tools.mima.plugin.MimaPlugin
import com.typesafe.tools.mima.plugin.MimaKeys.{previousArtifact, reportBinaryIssues, binaryIssueFilters}

object MimaSettings {

  private val mimaProblemFilters = {
    import com.typesafe.tools.mima.core._
    import com.typesafe.tools.mima.core.ProblemFilters._
    Seq(
      // since 2.2.0
      // private[scalikejdbc] methods were removed
      exclude[MissingMethodProblem]("scalikejdbc.DB.localTxForReturnType"),
      exclude[MissingMethodProblem]("scalikejdbc.DB.localTxForReturnType$default$3"),
      exclude[MissingMethodProblem]("scalikejdbc.NamedDB.localTxForReturnType"),
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.localTxForReturnType"),
      // newly added methods
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.localTx"),
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.localTxWithConnection"),
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.localTx"),
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.localTx$default$2"),
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.localTxWithConnection$default$2"),
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.localTxWithConnection"),
      exclude[MissingMethodProblem]("scalikejdbc.DB.localTx"),
      exclude[MissingMethodProblem]("scalikejdbc.DB.localTxWithConnection"),
      exclude[MissingMethodProblem]("scalikejdbc.NamedDB.localTx"),
      exclude[MissingMethodProblem]("scalikejdbc.NamedDB.localTxWithConnection"),
      // since 2.2.1
      exclude[MissingMethodProblem]("scalikejdbc.StatementExecutor.copy"),
      exclude[MissingMethodProblem]("scalikejdbc.StatementExecutor.apply"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.StatementExecutor.apply$default$4"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.StatementExecutor.<init>$default$4"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.StatementExecutor.copy$default$4"),
      exclude[MissingMethodProblem]("scalikejdbc.StatementExecutor.this"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.tags"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.scalikejdbc$DBSession$$_tags"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.scalikejdbc$DBSession$$_tags_="),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.scalikejdbc$DBSession$_setter_$scalikejdbc$DBSession$$_tags_="),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.scalikejdbc$DBSession$$super$using"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.DBSession.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.unexpectedInvocation"),
      // since 2.2.2
      exclude[MissingTypesProblem]("scalikejdbc.mapper.GeneratorConfig$"),
      exclude[MissingMethodProblem]("scalikejdbc.mapper.GeneratorConfig.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.mapper.GeneratorConfig.copy"),
      exclude[MissingMethodProblem]("scalikejdbc.mapper.GeneratorConfig.this"),
      // DBConnectionAttributes support
      exclude[MissingTypesProblem]("scalikejdbc.ConnectionPoolSettings$"),
      exclude[MissingTypesProblem]("scalikejdbc.ActiveSession$"),
      exclude[MissingMethodProblem]("scalikejdbc.ConnectionPoolSettings.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.ConnectionPoolSettings.copy"),
      exclude[MissingMethodProblem]("scalikejdbc.ConnectionPoolSettings.this"),
      exclude[MissingMethodProblem]("scalikejdbc.DB.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.DB.copy"),
      exclude[MissingMethodProblem]("scalikejdbc.DB.this"),
      exclude[MissingMethodProblem]("scalikejdbc.DBConnection.connectionAttributes"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.connectionAttributes"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.updateWithAutoGeneratedKeyNameAndFilters"),
      exclude[MissingMethodProblem]("scalikejdbc.ActiveSession.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.ActiveSession.copy"),
      exclude[MissingMethodProblem]("scalikejdbc.ActiveSession.this"),
      exclude[MissingMethodProblem]("scalikejdbc.ActiveSession.apply$default$2"),
      exclude[MissingMethodProblem]("scalikejdbc.ActiveSession.<init>$default$2"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.ActiveSession.apply$default$3"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.ActiveSession.copy$default$2"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.ActiveSession.copy$default$3"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.ActiveSession.copy$default$3"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.ActiveSession.<init>$default$3"),
      // tags for update/execute/batch
      exclude[MissingMethodProblem]("scalikejdbc.SQLBatch.this"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLExecution.this"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLUpdateWithGeneratedKey.this"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLUpdate.this"),
      // since 2.2.3
      exclude[MissingMethodProblem]("scalikejdbc.QueryDSLFeature#ConditionSQLBuilder.notBetween"),
      // since 2.2.4
      exclude[MissingMethodProblem]("scalikejdbc.mapper.GeneratorConfig.toString"),
      exclude[MissingMethodProblem]("scalikejdbc.mapper.CodeGenerator.scalikejdbc$mapper$CodeGenerator$$toClassName"),
      exclude[IncompatibleMethTypeProblem]("scalikejdbc.SQLToOptionImpl.this"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.SQLToListImpl.apply"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.SQLToOptionImpl.<init>$default$4"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.SQLToOptionImpl.apply"),
      exclude[IncompatibleResultTypeProblem]("scalikejdbc.SQLToTraversableImpl.apply"),
      exclude[MissingClassProblem]("scalikejdbc.createNameBindingSQL"),
      exclude[MissingClassProblem]("scalikejdbc.createNameBindingSQL$"),
      exclude[MissingClassProblem]("scalikejdbc.createSQL"),
      exclude[MissingClassProblem]("scalikejdbc.createSQL$"),
      exclude[MissingClassProblem]("scalikejdbc.OneToManySQL$"),
      exclude[MissingClassProblem]("scalikejdbc.OneToOneSQL$"),
      exclude[MissingClassProblem]("scalikejdbc.OutputDecisions"),
      exclude[MissingClassProblem]("scalikejdbc.SQLToListImpl$"),
      exclude[MissingClassProblem]("scalikejdbc.SQLToTraversableImpl$"),
      exclude[MissingMethodProblem]("scalikejdbc.AllOutputDecisionsUnsupported.collection"),
      exclude[MissingMethodProblem]("scalikejdbc.AllOutputDecisionsUnsupported.toCollection"),
      exclude[MissingMethodProblem]("scalikejdbc.DBSession.collection"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToManySQL.this"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToManySQLToOption.output"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToManySQLToOption.this"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToOneSQL.this"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToOneSQLToOption.output"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToOneSQLToOption.this"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToXSQL.<init>$default$3"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToXSQL.this"),
      exclude[MissingMethodProblem]("scalikejdbc.SQL.<init>$default$4"),
      exclude[MissingMethodProblem]("scalikejdbc.SQL.this"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToList.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToList.result"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToListImpl.this"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToOption.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToOption.isSingle"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToOption.output"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToOption.result"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToOptionImpl.output"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToTraversable.apply"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToTraversable.result"),
      exclude[MissingMethodProblem]("scalikejdbc.SQLToTraversableImpl.this"),
      exclude[MissingTypesProblem]("scalikejdbc.OneToManies2SQLToList"),
      exclude[MissingTypesProblem]("scalikejdbc.SQLToListImpl"),
      exclude[MissingTypesProblem]("scalikejdbc.SQLToOptionImpl"),
      exclude[MissingTypesProblem]("scalikejdbc.SQLToTraversableImpl"),
      exclude[UpdateForwarderBodyProblem]("scalikejdbc.SQLToList.apply$default$2"),
      exclude[UpdateForwarderBodyProblem]("scalikejdbc.SQLToOption.apply$default$2"),
      exclude[UpdateForwarderBodyProblem]("scalikejdbc.SQLToTraversable.apply$default$2"),
      exclude[MissingMethodProblem]("scalikejdbc.AuthenticatedDataSourceConnectionPool.this"),
      exclude[MissingMethodProblem]("scalikejdbc.DataSourceConnectionPool.this")
    ) ++ (for{
      clazz <- Seq("OneToManies2SQLToList", "SQLToListImpl", "SQLToOptionImpl", "SQLToTraversableImpl")
      method <- Seq("first", "headOption", "list", "single", "toList", "toOption", "toTraversable", "traversable")
    } yield {
      exclude[IncompatibleResultTypeProblem]("scalikejdbc." + clazz + "." + method)
    }) ++ (2 to 9).flatMap{ n => Seq(
      exclude[MissingClassProblem]("scalikejdbc.OneToManies" + n + "SQL$"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToManies" + n + "SQL.this"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToManies" + n + "SQLToOption.output"),
      exclude[MissingMethodProblem]("scalikejdbc.OneToManies" + n + "SQLToOption.this")
    )}
  }

  val mimaSettings = MimaPlugin.mimaDefaultSettings ++ Seq(
    previousArtifact := Some(organization.value % s"${name.value}_${scalaBinaryVersion.value}" % "2.2.0"),
    test in Test := {
      reportBinaryIssues.value
      (test in Test).value
    },
    binaryIssueFilters ++= mimaProblemFilters
  )

}
