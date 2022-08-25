package me.tabinol.secuboid.generator.flags;

import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.jupiter.api.Test;

import me.tabinol.secuboid.generator.common.BufferedWriterArray;

class FlagsGeneratorTest {

        private static final String YAML_STR = ""
                        + "action-test:\n"
                        + "  type: action\n"
                        + "  need-source: false\n"
                        + "  description:\n"
                        + "    en: Action test\n"
                        + "    fr: Action test\n"
                        + "source-action-test:\n"
                        + "  type: source-action\n"
                        + "  description:\n"
                        + "    en: Source action test\n"
                        + "    fr: Source action test\n"
                        + "source-action-target-test:\n"
                        + "  type: source-action-target\n"
                        + "  need-target: true\n"
                        + "  description:\n"
                        + "    en: Source action target test\n"
                        + "    fr: Source action target test\n"
                        + "metadata-test:\n"
                        + "  type: metadata\n"
                        + "  hidden: true\n"
                        + "  need-metadata: true\n"
                        + "  value-class: String.class\n"
                        + "  from-string: v -> v\n"
                        + "  description:\n"
                        + "    en: Metadata test\n"
                        + "    fr: Metadata test\n";

        private static final String JAVA_TEMPLATE_STR = "{{generatedFlags}}";

        private final Map<String, String> languageToTarget = singletonMap("en", null);

        private final FlagsGenerator flagsGenerator = new FlagsGenerator(null, null, null, null, languageToTarget);

        @Test
        void when_generate_code_then_return_target_with_all_types() throws MojoExecutionException, IOException {
                StringWriter swJavaTarget = new StringWriter();
                StringWriter swLangTarget = new StringWriter();
                StringWriter[] swJavaTargets = new StringWriter[] { swJavaTarget, swLangTarget };

                try (
                                InputStream isSource = new ByteArrayInputStream(YAML_STR.getBytes()); //
                                //
                                StringReader srJavaTemplate = new StringReader(JAVA_TEMPLATE_STR); //
                                BufferedReader brJavaTemplate = new BufferedReader(srJavaTemplate); //
                                //
                                BufferedWriterArray bufferedWriterArray = new BufferedWriterArray(swJavaTargets); //
                ) {
                        flagsGenerator.generate(isSource, brJavaTemplate, bufferedWriterArray);
                }

                String output = swJavaTarget.toString();

                assertTrue(output.contains("@FlagRegistered"));
                assertTrue(output.contains(
                                "public static final FlagType FLAG_ACTION_TEST = new FlagType(\"action-test\", \"Action test\", false, false, false, false);"));
                assertTrue(output.contains(
                                "public static final FlagType FLAG_SOURCE_ACTION_TEST = new FlagType(\"source-action-test\", \"Source action test\", true, false, false, false);"));
                assertTrue(output.contains(
                                "public static final FlagType FLAG_SOURCE_ACTION_TARGET_TEST = new FlagType(\"source-action-target-test\", \"Source action target test\", true, true, false, false);"));
                assertTrue(output.contains(
                                "public static final FlagType FLAG_METADATA_TEST = new FlagType(\"metadata-test\", \"Metadata test\", true, false, true, true);"));

                String langOutput = swLangTarget.toString();

                assertTrue(langOutput.contains("action-test: Action test"));
        }
}
