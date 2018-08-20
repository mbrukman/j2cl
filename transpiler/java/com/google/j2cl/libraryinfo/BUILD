package(default_visibility = [
    "//transpiler:__subpackages__",
])

# Apache2
licenses(["notice"])

proto_library(
    name = "library_info_proto",
    srcs = ["library_info.proto"],
)

java_proto_library(
    name = "library_info_java_proto",
    deps = [":library_info_proto"],
)

java_library(
    name = "libraryinfo",
    srcs = glob(["*.java"]),
    exports = [":library_info_java_proto"],
    deps = [
        ":library_info_java_proto",
        "//third_party:guava",
        "//third_party:jsr305_annotations",
        "//third_party:protobuf_json",
        "//transpiler/java/com/google/j2cl/ast",
    ],
)