//
//  HTTPMethod.swift
//  GitHubSearchRepository
//
//  Created by SHIKI TAKAHASHI on 2017/11/09.
//  Copyright © 2017年 SHIKI TAKAHASHI. All rights reserved.
//

import Foundation

enum HTTPMethod: String {
    case get = "GET"
    case post = "POST"
    case put = "PUT"
    case head = "HEAD"
    case delete = "DELETE"
    case patch = "PATCH"
    case trace = "TRACE"
    case options = "OPTIONS"
    case connect = "CONNECT"
}
