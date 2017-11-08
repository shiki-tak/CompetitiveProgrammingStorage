//
//  GitHubClientError.swift
//  GitHubSearchRepository
//
//  Created by SHIKI TAKAHASHI on 2017/11/09.
//  Copyright © 2017年 SHIKI TAKAHASHI. All rights reserved.
//

import Foundation

enum GitHubClientError: Error {
    case connectionError(Error)
    
    case responseParseError(Error)
    
    case apiError(GitHubAPIError)
}
