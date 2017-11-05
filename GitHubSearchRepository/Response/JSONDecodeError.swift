//
//  JSONDecodeError.swift
//  GitHubSearchRepository
//
//  Created by SHIKI TAKAHASHI on 2017/11/05.
//  Copyright © 2017年 SHIKI TAKAHASHI. All rights reserved.
//

import Foundation

enum JSONDEcodeError: Error {
    case invalidFormat(json:Any)
    case missingValue(key: String, actualValue: Any?)
}
