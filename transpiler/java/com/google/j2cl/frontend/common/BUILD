# Description:
#   This package contains frontend common code that is agnostic of the actual frontend used.

package(default_visibility = [
    "//transpiler/java/com/google/j2cl/frontend:__subpackages__",
])

licenses(["notice"])  # Apache 2.0

java_library(
    name = "common",
    srcs = glob(["*.java"]),
    deps = [
        "//third_party:auto_value",
        "//third_party:guava",
        "//third_party:gwt-jsinterop-annotations",
        "//third_party:jsr305_annotations",
        "//transpiler/java/com/google/j2cl/ast",
        "//transpiler/java/com/google/j2cl/common",
    ],
)
