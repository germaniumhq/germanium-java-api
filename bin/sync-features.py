#!/usr/bin/env python

import os
import shutil

WALK_FOLDER = "../germanium/features"
TARGET_FOLDER = "germanium-tests/src/test/resources/features"

def is_in_features(name: str) -> bool:
    return name.startswith("%s/features" % (WALK_FOLDER))


def is_in_test_site(name: str) -> bool:
    return name.startswith("%s/test-site" % (WALK_FOLDER))


for folder, directories, files in os.walk(WALK_FOLDER):
    if not is_in_features(folder) and not is_in_test_site(folder):
        continue

    relative_folder = folder[len(WALK_FOLDER) + 1:]
    os.makedirs("%s/%s" % (TARGET_FOLDER, relative_folder), exist_ok=True)

    for file in files: # type: str
        if is_in_features(folder) and not file.endswith(".feature"):
            continue

        shutil.copy("%s/%s" % (folder, file),
                    "%s/%s/%s" % (TARGET_FOLDER, relative_folder, file))
